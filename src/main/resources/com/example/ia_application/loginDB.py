import sys
import os
import datetime
import pydbhub.dbhub as dbhub

# Create a new DBHub.io API object
db = dbhub.Dbhub(config_file=f"{os.path.join(os.path.dirname(__file__), 'configUsers.ini')}")

# Retrieve the metadata for the remote database
r, err = db.Query(
    "viraj_g",
    "Users.sqlite",
    '''
    SELECT name
    FROM Logins
    WHERE username == 't' 
    AND password == 'tt'
    '''
)
if err is not None:
    print(f"[ERROR] {err}")
    sys.exit(1)
for row in r:
