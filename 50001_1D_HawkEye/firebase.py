from libdw import pyrebase
import cv2
import numpy as np 
from PIL import Image
def change_value():
    url ="https://fir-test-8c15d.firebaseio.com/"#"https://java1d.firebaseio.com/"
    apikey = "AIzaSyCOj5VmBPcENI3WuZaXnwEdc016wk-Xe7k"#"AIzaSyD_szQHeqkFF9f4FzZzh0FBseupcV8O0kk"
    config = {"apiKey": apikey,"databaseURL": url }#required details for the firebase database
    firebase = pyrebase.initialize_app(config)#initializing firebase
    db =firebase.database()#creating a database object to acccess firebase realtime database
    print("Trying to authorize " )
    try:
        f = open("roomcount.txt", "r", encoding = "utf_8")
        print("herrrr")

        namecount = []
        for line in f:
            namecount.append(line.strip())
        f.close()
      
      
        
        db.child("Locations").child(namecount[0]).child("number").set(int(namecount[1]))#Giving authorization to the student ID that was sent as input
        db.child("Locations").child(namecount[0]).child("occupancy rate").set(int(50))
        print(5)
        total = db.child("Locations").child(namecount[0]).child("total capacity").get().val()
        db.child("Locations").child(namecount[0]).child("occupancy rate").set(int(namecount[1])/int(total))

        print("Authorization successful")
    except:#take care of exceptions
        print("ERROR: Authorization failed. Try again")
change_value()