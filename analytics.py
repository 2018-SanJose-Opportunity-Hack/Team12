import pandas as pd
import mysql.connector
import datetime
import time
import matplotlib.pyplot as plt

def parseFile(fileName):
    print(fileName)
    data = pd.read_excel("PayPalHackTestFile.xlsx")

    conn = mysql.connector.connect(
        host='192.168.86.16',
        database='DevPost',
        password='root',
        user='monty'
    )

    try:
        if not conn.is_connected():
            print("Connection Failed!")
            return
    except:
        print("Error while making a connection!")

    cursor = conn.cursor()
    strSQL = ""
    strSQL = "select survey_id, count(question_id), avg(time_filled) from survey_response group by survey_id;"

    cursor = conn.cursor()
    cursor.execute(strSQL)

    records = cursor.fetchall()
    print(records)
