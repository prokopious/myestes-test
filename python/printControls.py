from pywinauto.application import Application
import time
import sys

app = Application().connect(path=r"C:\Program Files (x86)\Estes Express Line\DockWorkerAppInstaller\eDock.exe")


argArray = sys.argv[1].split(",")


if len(argArray) == 1:

    app[argArray[0]].print_control_identifiers()
    
if len(argArray) == 2:

    app[argArray[0]][argArray[1]].print_control_identifiers()
    
if len(argArray) == 3:

    app[argArray[0]][argArray[1]][argArray[2]].print_control_identifiers()
    
if len(argArray) == 4:

    app[argArray[0]][argArray[1]][argArray[2]][argArray[3]].print_control_identifiers()


