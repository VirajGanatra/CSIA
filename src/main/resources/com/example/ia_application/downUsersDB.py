import sys
import os
import datetime
import pydbhub.dbhub as dbhub

# Create a new DBHub.io API object
db = dbhub.Dbhub(config_file=f"{os.path.join(os.path.dirname(__file__), 'configUsers.ini')}")

dbName = "Users.sqlite"
buf, err = db.Download(db_name=dbName, db_owner="viraj_g")
if err is not None:
    print(err)
    sys.exit(1)

with open(dbName, "wb") as sqlite_file:
    sqlite_file.write(buf)


