import json
import sys
import os
import datetime
import pydbhub.dbhub as dbhub

user = sys.argv[1]
password = sys.argv[2]
username = "\'"+user+"\'"
# print(username)
# print(password)
# print('''
#     SELECT password
#     FROM Logins
#     WHERE username == ''' + username)

# Create a new DBHub.io API object
db = dbhub.Dbhub(config_file=f"{os.path.join(os.path.dirname(__file__), 'configUsers.ini')}")

#Retrieve the metadata for the remote database
r, err = db.Query(
    "viraj_g",
    "Users.db",
    '''
    SELECT password
    FROM Logins
    WHERE username == ''' + username
)



if err is not None:
    print(f"[ERROR] {err}")
    sys.exit(1)
for row in r:
    if row["Password"] == password:
        print("True")
    else:
        print("False")

