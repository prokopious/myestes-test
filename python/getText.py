from calendar import c
from pywinauto.application import Application
import time
import sys

app = Application().connect(path=r"C:\Program Files (x86)\Estes Express Line\DockWorkerAppInstaller\eDock.exe")

argArray = sys.argv[1].split(",")

if len(argArray) == 1:

    textElement = app[argArray[0]]
    print("myVariable" + textElement.texts()[0])

if len(argArray) == 2:

    textElement = app[argArray[0]][argArray[1]]
    print("myVariable" + textElement.texts()[0])
    
if len(argArray) == 3:

    textElement = app[argArray[0]][argArray[1]][argArray[2]]
    print("myVariable" + textElement.texts()[0])
    
if len(argArray) == 4:

    textElement = app[argArray[0]][argArray[1]][argArray[2]][argArray[3]]
    print("myVariable" + textElement.texts()[0])
    
