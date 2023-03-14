import sys
import os
import datetime
import pydbhub.dbhub as dbhub

# Create a new DBHub.io API object
db = dbhub.Dbhub(config_file=f"{os.path.join(os.path.dirname(__file__), '..', 'configUsers.ini')}")


info = dbhub.UploadInformation(
    commitmsg="Add User",
    committimestamp=datetime.datetime.now(),
)

try:
    # Read the database file
    myDB = os.path.join(os.path.dirname(__file__), 'Users.db')
    f = open(myDB, 'rb')
except FileNotFoundError:
    print(f"File {myDB} not found.  Aborting")
    sys.exit(1)
except OSError:
    print(f"OS error occurred trying to open {myDB}")
    sys.exit(1)
except Exception as err:
    print(f"Unexpected error opening {myDB} is", repr(err))
    sys.exit(1)
else:
    with f:
        res, err = db.Upload(db_name='Users.sqlite', info=info, db_bytes=f)
        if err is not None:
            print(err)
            sys.exit(1)
        else:
            os.remove(os.path.join(os.path.dirname(__file__), 'Users.db'))
