import keyboard
import time
import sys

time.sleep(7)

keyString = sys.argv[1].replace(",", " ");
keystrokes = keyString.split();

print("typing keys: " + keyString)
keyboard.send("tab")
keyboard.send("tab")

for key in keystrokes:
  keyboard.send(key)
  time.sleep(1)
