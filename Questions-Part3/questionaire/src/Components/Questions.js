import React, {Component} from 'react';
import Question from './Question';
import './Questions.css';
import axios from 'axios';

class Questions extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            records: []
        }
        this.getData();
    }

    deleteData = (id) => {
        let url = "http://localhost:5600/Questions/" + id;
        console.log(url);
        axios.delete(url)
          .then((resp) => {
            console.log(resp);
            if(resp.data === "Success"){
                console.log("Successfully deleted the data");
                this.getData();
            }
          });
    }

    getData = () => {
        let url = "http://localhost:5600/Questions";
        axios.get(url)
          .then((response) => {
              console.log(response);
              this.setState({
                records: response.data
            })
        });
    }
    
    render(){
        let questionList = "";
        if(this.state.records.length > 0){
            questionList = this.state.records.map((elem) => {
                return <Question id={elem.question_ID} onDelete={this.deleteData} key={elem.question_ID} header={elem.question_text} selected={elem.priority} />
            });
        }
        
        return (
            <div>
                <label className="lblHeader">Existing Questions</label>
                {questionList}
            </div>
        );
    }
}

export default Questions;