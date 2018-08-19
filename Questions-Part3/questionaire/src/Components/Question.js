import React from 'react';
import './Question.css';

const Question = (props) => {

    const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    let options = numbers.map((num) => {
        if(num === props.selected){
            return <option key={num} selected value={num}>{num}</option>;
        }
        return <option key={num} value={num}>{num}</option>;
    });
    let DropDownn = (
        <select className="dropDown">
            {options}
        </select>
    );

    return (
        <div>
            <div className="itemHolder">
                <button className="btnplus" onClick={() => props.onDelete(props.id)}>-</button>
                <div className="editContainer">
                    <label>{props.header}</label>
                </div>
                <div>
                    {DropDownn}
                </div>
            </div>
            <div className="sep"/>
        </div>
    );
}

export default Question;