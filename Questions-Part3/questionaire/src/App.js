import React, { Component } from 'react';
import './App.css';
import Questions from './Components/Questions';
import axios from 'axios';

class App extends Component {
  
  constructor(props){
    super(props);
    this.child = React.createRef();
  }

  updateInputValue = () => {
    console.log("Came here anyway!!!");
    let obj = {
        question_query: this.textInput.value,
        priority: this.dropD.value
    }
    let url = "http://localhost:5600/Questions/" + obj.question_query + "@" + obj.priority;
    console.log(url);
    axios.post(url)
      .then((response) => {
        console.log(response);
        this.child.current.getData();
      });
  }
  
  render() {
    const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    let options = numbers.map((num) => {
      return <option key={num} value={num} ref={(dropD) => this.dropD = dropD} >{num}</option>;
    });
    let DropDownn = (
        <select className="dropDown">
            {options}
        </select>
    );
   
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Questionnaire</h1>
        </header>
        <label className="lblHeader">Add a Question</label>
        <div className="inputContainer">
          <button className="btnplus" onClick={this.updateInputValue}>+</button>
          <input type="text" ref={(input) => this.textInput = input} className="txtInput"></input>
          {DropDownn}    
        </div>
        <div className="sep"/>
        <Questions />
      </div>
    );
  }
}

export default App;
