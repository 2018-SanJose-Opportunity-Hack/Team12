const express = require('express');
const app = express();
const mysql = require('mysql');
let cors = require('cors');

app.use(cors());

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "DevPost"
});

con.connect(function(err){
    if (err) throw err;
    console.log("Connected to the server");
});

app.get("/Questions", (req, res) => {
    let sql = "select question_ID,question_text,for_mentor,priority from questions Order by priority desc";
    con.query(sql, function (err, result) {
        if (err) throw res.send("Error while processing the request");
        res.send(result);
    });
});

app.delete("/Questions/:id", (req, res) => {
    let sql = "Delete from questions where question_ID='" + req.params.id + "'";
    console.log("Received a delete request");
    console.log("Executing SQL" + sql);
    con.query(sql, function(err, result){
        if (err) res.send("Error while processing the request");
        res.send("Success")
    })
});

app.post("/Questions/:Text", (req, res) => {
    console.log(req.params.Text);
    var strr = req.params.Text.split("@");
    console.log(strr);

    let sql = "Insert into questions(question_text, priority) Values ('" + strr[0] + "','" + strr[1] + "')";
    console.log("Insert query:" + sql);
    con.query(sql , function(err, result){
        if (err) res.send("Error while processing the request");
        res.send("Success");
    });
});

app.listen(5600, () => console.log("Server listening on port 5600"));
