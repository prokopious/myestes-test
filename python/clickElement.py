from calendar import c
from pywinauto.application import Application
import time
import sys

app = Application().connect(path=r"C:\Program Files (x86)\Estes Express Line\DockWorkerAppInstaller\eDock.exe")
time.sleep(5)

print("Click element")

argArray = sys.argv[1].split(",")

if len(argArray) == 2:

    clickableElement = app[argArray[0]][argArray[1]]
    clickableElement.click_input()
    
if len(argArray) == 3:

    clickableElement = app[argArray[0]][argArray[1]][argArray[2]]
    clickableElement.click_input()
    
if len(argArray) == 4:

    clickableElement = app[argArray[0]][argArray[1]][argArray[2]][argArray[3]]
    clickableElement.click_input()
    
    