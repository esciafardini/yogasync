# Yogasync
Since the pandemic, many yoga studios have been forced to close their doors.  Yoga teachers are doing classes in gyms or via zoom.  The goal of this application is to enable teachers to share their schedules with students in one place.  A student can open the app and see all of the Baltimore yoga classes happening during the week.

### Create image & run container from docker-compose file
docker-compose -f docker-compose.yml up -d

### Connect to DB from terminal
psql -h localhost -p 8880 -U kingchungus -d yogasync
- password is in env file
