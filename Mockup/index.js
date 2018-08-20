const express = require('express');
var exphbs  = require('express-handlebars');
const app = express();
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

var hbs = exphbs.create({});


app.engine('handlebars', hbs.engine);
app.set('view engine', 'handlebars');
app.use(express.static(__dirname));

app.get('/', function (req, res){
  res.render('mockup',{hashID:req.query.hashID});
});

app.get('/id/:hashID',function  (req, res){
  res.render('mockup',{hashID:req.query.hashID});
});


app.get('/hours',function  (req, res){
  res.send(req.body);
});

app.listen(3000, () => console.log('Example app listening on port 3000!'))
