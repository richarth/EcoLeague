/*
Copyright (c) 2012 Virtual-Techno.com Ltd

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Version 0.1 2012-06-29

EcoLeague: energy, cost and carbon calculations 

In this version this is called when any client views the league table.
Also, the links to AMEE and a source of costs is not yet in place.
In a future version most of the work will be done when submissions are made.  Results will be stored with ZADD.  Then when table is needed for viewing it can be obtained with ZRANGE.
*/

/*
object sort on score element
*/
function sortObj(objects) {
  objects.sort(function(a, b) {
    return (a.score < b.score);
  });
  return objects;
}
/*
improvement between two submissions
much more to do to get a complete score
in retrospect the storage format is not ideal - has to search for known types
*/
improvement = function(previous, latest, func) {
  //console.log("previous",JSON.stringify(previous));
  //console.log("latest",JSON.stringify(latest));
  var delta={};
  var lfuels=latest.fuels, lfuel=[];
  var pfuels=previous.fuels, pfuel=[];
  var fueltypes=['electricity','gas','coal','oil'];
  var penergy=0, lenergy=0;
  for (i in lfuels) {
    for (j in fueltypes) {
      var ft=fueltypes[j];
      if (lfuels[i][ft]) {
        lfuel[ft]=lfuels[i][ft];
        pfuel[ft]=pfuels[i][ft];
        // insert here: conversion to energy cost and carbon
        // insert here scaling by proportion of a month between submissions
        lenergy+=lfuel[ft];
        penergy+=pfuel[ft];
      }
    }
  }
  //console.log(lfuel,lenergy,pfuel,penergy);
  delta.energy=Math.floor((lenergy-penergy)/penergy*100+.5);
  func(delta);
}

exports.crank = function(key,func) {
  var result=[], acnames=[];
  db.keys("account:*",function(err,accounts) {
    accounts.forEach(function(account,i) {
      db.get(account,function(err,acdata) {
        acnames[i]=JSON.parse(acdata).name;
        account=account.replace('account:','');
        //console.log("account",account);
        var previous, latest;
        // get last 2 submissions from this account
        db.lrange("usersubs:"+account,-2,-1,function(err, submissions) {
          if (submissions.length > 1) {
            submissions.forEach(function(submission,j) {
              db.get(submission, function (err, body) {
                //console.log("submission",account, j, body);
                try {
                  if (j > 0) {
                    previous=JSON.parse(body);
                  } else {
                    latest=JSON.parse(body);
                  }
                } catch (err) {
                  console.log("parse error",err.message);
                }
                if (j >= submissions.length-1) {
                  improvement(previous, latest, function(delta) {
                    //console.log("delta",account,JSON.stringify(delta));
                    result.push({username:acnames[i],score:delta.energy})
                    if (result.length >= accounts.length) {
                      result=sortObj(result);
                      for (k in result) result[k].rank=parseInt(k)+1;
                      //console.log("result",result);
                      func({
                        energy:result.slice(0,10),
                        cost:result.slice(0,10),
                        carbon:result.slice(0,10)
                      });
                    }
                  });
                }
              });  
            }); 
          } else {
            result.push({username:acnames[i],score:0})
          }
        }); 
      });
    });  // accounts loop
  });
}
