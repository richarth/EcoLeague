/*
Copyright (c) 2012 Virtual-Techno.com Ltd

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Version 0.1 2012-06-29

EcoLeague: API 

In this version we have a test option that produces data.  This is only needed for testing clients before a useful number of accounts have been created and submissions made.  See also comments in processing.js concerning when things are done. Additionally this should be modularised so new command can easily be plussed in.
*/
/*
Server that handles user database and pachube registration
*/
var redis = require("redis");
global.db = redis.createClient();
var sets=require("./settings.js");
var crypto = require('crypto');
var proc = require('./processing.js');

db.on("error", function (err) {
    console.log("Error " + err);
});

// start redis like so redis-server ~Documents/Development/redis.conf >redis.log&

start_htserver=function(route) {
  var handle = {}
    handle["/"] = api;
    handle["/start"] = api;
  var http = require("http");
  var url = require("url");
  //var querystring = require("querystring");

  function route(handle, pathname, response, postData) {
    //console.log("About to route a request for " + pathname);
    if (typeof handle[pathname] === 'function') {
      handle[pathname](response, postData);
    } else {
      console.log("No request handler found for " + pathname);
      response.writeHead(404, {"Content-Type": "text/plain"});
      response.write("404 Not found");
      response.end();
    }
  }

  function api(response, data) {
    //console.log("Request handler 'api' was called.");
    if (data=="") data="{}";
    //console.log("posted=",data,"=");
    try {
        do_process(JSON.parse(data),function(msg) {
         response.writeHead(200, {"Content-Type": "application/json"});
         response.write(JSON.stringify(msg));
         response.end();
        });
    } catch (err) {
        console.log("parse error", data, err);
        //process.exit();
    }
  }

  function onRequest(request, response) {
    var postData = "";
    var pathname = url.parse(request.url).pathname;
    //console.log("Request for " + pathname + " received.");
    request.setEncoding("utf8");
    request.addListener("data", function(postDataChunk) {
      postData += postDataChunk;
      //console.log("Received POST data chunk '" + postDataChunk + "'.");
    });

    request.addListener("end", function() {
      route(handle, pathname, response, postData);
    });
  }

  http.createServer(onRequest).listen(9988);
  console.log("Server has started.");
}

do_process = function(data,func) {
  var command=data.command;
  //console.log("command=",command);
  switch(command) {
    case 'register' : register(data.args,func); break;
    case 'submit' : submit(data.args,func); break;
    case 'results' : results(data.args,func); break;
    case 'results2' : results2(data.args,func); break;
    default: console.log("command not recognised"); func({error: "command not recognised"});
  }
  return "bad comand";
}

/*
register a user
*/
register = function(args,func) {
  console.log("register", JSON.stringify(args));
  var email=args.email;
  var key;
  try {
    key=crypto.createHash('md5').update(email).digest('hex');
  } catch (err) {
    console.log("err",err);
    key="kkkkk";
  }
  //console.log("e",email,"k",key);
  db.set("account:"+key, JSON.stringify(args), function (err,result) {
    func({status:result,user_key:key});
  });
}

/*
submit readings
*/
submit = function (args,func) {
  console.log("submit", JSON.stringify(args));
  var key=args.user_key;
  var date=args.date;
  delete args.user_key;
  delete args.date;
  db.set("submission:"+key+":"+date, JSON.stringify(args), function (err,result) {
    if (result) {
      db.lpush("usersubs:"+key,"submission:"+key+":"+date, function (err,result) {
        func({status:result});
      });
    }
  });
}

/*
calculate and deliver results
*/
results = function (args,func) {
  var key=args.user_key;
  console.log("results2",key);
  try {
    proc.crank(key, function(results) {
      console.log("results",JSON.stringify(results));
      func({status:"ok",results:results});
    });
  } catch (err) {
    console.log("error in processing",err.message);
  }
}

results_test = function (args,func) {
  var key=args.user_key;
  console.log("results",key);
  var results={
    "energy": [
        {
            "rank": 1,
            "username": "albert",
            "score": 66
        },
        {
            "rank": 2,
            "username": "betty",
            "score": 61
        },
        {
            "rank": 3,
            "username": "charles",
            "score": 60
        },
        {
            "rank": 4,
            "username": "daniel",
            "score": 55
        },
        {
            "rank": 5,
            "username": "ellen",
            "score": 54
        }
    ],
    "carbon": [
        {
            "rank": 1,
            "username": "albert",
            "score": 72
        },
        {
            "rank": 2,
            "username": "betty",
            "score": 54
        },
        {
            "rank": 3,
            "username": "charles",
            "score": 44
        },
        {
            "rank": 4,
            "username": "daniel",
            "score": 44
        },
        {
            "rank": 5,
            "username": "ellen",
            "score": 29
        }
    ],
    "cost": [
        {
            "rank": 1,
            "username": "albert",
            "score": 71
        },
        {
            "rank": 2,
            "username": "betty",
            "score": 62
        },
        {
            "rank": 3,
            "username": "charles",
            "score": 58
        },
        {
            "rank": 4,
            "username": "daniel",
            "score": 49
        },
        {
            "rank": 5,
            "username": "ellen",
            "score": 42
        }
    ]
};
    console.log("results",JSON.stringify(results));
    func({status:"ok",results:results});
}

start_htserver();
