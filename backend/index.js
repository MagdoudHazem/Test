const mysql=require('mysql');
const express=require('express');
var app=express();
const bodyparser=require('body-parser');
app.use(bodyparser.json());

 
var mysqlConnection=mysql.createConnection({
    host:'localhost',
    user :'root',
    password:'',
    database:'orange'



});

mysqlConnection.connect((err)=>
{
    if(!err){
        console.log('DB coonection succeed');
    }
    else console.log('DBconnection failed');

} 
);
app.listen(3000,()=>console.log('Express server is running at port 3000'))
app.get('/items',(req,res)=>{
    mysqlConnection.query('SELECT * FROM items',(err,rows,fields)=>{
        if(!err){
        res.send(rows);
        for (let p of rows)
        {
            console.log(p.id);

        }

    }
        else {
        console.log(err); }

    })
})




app.post('/items/add',(req,res)=>
{
    let u=req.body;
    console.log(req);
    var sql ="INSERT INTO items (title,macadress,model,ipadress,etat,date) VALUES (?,?,?,?,?,?)" ;
    mysqlConnection.query(sql,[u.title,u.macadress,u.model,u.ipadress,u.etat,u.date],(err,rows,fields)=>{
        if(!err){
        res.setHeader('Content-Type', 'application/json');
        res.send(JSON.stringify({ "success": 1 , "message" : " user added successfully" },undefined,2));   }     
        else  
        console.log(err);
    })

})


