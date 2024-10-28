from telethon import TelegramClient, events, sync, utils
from pygame import mixer
import threading
import time
import datetime

date = 'Unknown'

api_id = 41507
api_hash = '408a2082ea2c8d902466b206923c77ee'
th = None
sound = None

mixer.init()

print('---------------------------------------------------------')
print('STARTED AT', datetime.datetime.now(), 'JAI MATA JI /\\')
print('---------------------------------------------------------')
print()
print('Select sound length:')
print(' 1. Short')
print(' 2. Long')
sip = input('Enter : ')
print()

if(sip=='1'):
    sound = 'C:/Users/rusha/Desktop/short_alarm.mp3'
elif(sip=='2'):
    sound = 'C:/Users/rusha/Desktop/alarm.mp3'
else:
    sound = 'C:/Users/rusha/Desktop/short_alarm.mp3'

def alert():
    print('FOUND GO AT', datetime.datetime.now(), ' | ', date)
    mixer.music.load(sound)
    mixer.music.play()
    stop_alert()

def stop_alert():
    time.sleep(5)
    mixer.music.stop()

client = TelegramClient('session_name', api_id, api_hash)
client.start()

@client.on(events.NewMessage(pattern="go+|Go+|GO+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    if ('logout' not in event.raw_text and 'Logout' not in event.raw_text and 'LOGOUT' not in event.raw_text):
        th = threading.Thread(target=alert())
        th.start()

@client.on(events.NewMessage(pattern=".+go|.+Go|.+GO"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    if ('logout' not in event.raw_text and 'Logout' not in event.raw_text and 'LOGOUT' not in event.raw_text):
        th = threading.Thread(target=alert())
        th.start()

@client.on(events.NewMessage(pattern="open+|Open+|OPEN+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern=".+open|.+Open|.+OPEN"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern="opened+|Opened+|OPENED+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern=".+opened|.+Opened|.+OPENED"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern="available+|Available+|AVAILABLE+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    if('not' not in event.raw_text and 'Not' not in event.raw_text):
        th = threading.Thread(target=alert())
        th.start()

@client.on(events.NewMessage(pattern=".+available|.+Available|.+AVAILABLE"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    if ('not' not in event.raw_text and 'Not' not in event.raw_text):
        th = threading.Thread(target=alert())
        th.start()

@client.on(events.NewMessage(pattern="nov+|Nov+|NOV+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern=".+nov|.+Nov|.+NOV"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern="dec+|Dec+|DEC+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern=".+dec|.+Dec|.+DEC"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern="mar+|Mar+|MAR+"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()

@client.on(events.NewMessage(pattern=".+mar|.+Mar|.+MAR"))
async def handler(event):
    global date
    chat_from = event.chat if event.chat else (await event.get_chat())
    chat_title = utils.get_display_name(chat_from)
    date = chat_title + ' : ' + event.raw_text
    th = threading.Thread(target=alert())
    th.start()


client.run_until_disconnected()





