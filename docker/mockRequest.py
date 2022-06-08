import requests
import json

QUIZ_ID = '62a098ecfa20a95081cc785a'
HOST = 'http://localhost:3000'
URL = '{0}/quiz/submit/{1}'.format(HOST, QUIZ_ID)
TOKEN = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Mjk1MjAzOWQ2MDBkNDVmNTVjMTRjMWQiLCJ1c2VybmFtZSI6InphQm9nZGFuIiwicm9sZSI6MTAxMTAsImZyZXNoIjpmYWxzZSwiaWF0IjoxNjU0NzI0NDIyLCJleHAiOjE2NTQ4MTA4MjJ9.NogC6YSNseBY-TjwuJI3caQQ-ORIy0VYYoS7In9cvL0'
with open('mock.java') as f:
    data = f.read();
print(json.dumps(data))
payload = [
    {
        "questionUID": "1e5102ce61d24b7697c5a96b07464b1a",
        "response": data
    }
]

# print(json.dumps(payload, indent=2))
res = requests.post(
    URL, 
    data=json.dumps(payload), 
    headers={
        'Authorization': 'Bearer ' + TOKEN, 
        'Content-Type': 'application/json'
    }
)
print(res.text)