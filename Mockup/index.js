const express = require('express');
var exphbs  = require('express-handlebars');
const app = express();

var hbs = exphbs.create({});

app.engine('handlebars', hbs.engine);
app.set('view engine', 'handlebars');
app.use(express.static(__dirname));

app.get('/', (req, res) => res.render('mockup',{hashID:req.query.hashID}))

app.get('/:hashID', (req, res) => res.render('mockup',{hashID:req.query.hashID}))

app.listen(3000, () => console.log('Example app listening on port 3000!'))
