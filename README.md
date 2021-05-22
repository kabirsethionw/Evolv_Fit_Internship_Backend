# Evolv_Fit_Internship_Backend
A Spring boot project that uses MongoDB Atlas to persist data of user account, blogs, and comments made on blogs.

**REASON OF USING MongoDB**
This application of blogs maintains data of blogs, user and comments. This application focuses less on record keeping and more on flexible data with less fields, therefore database with unstructure documents can handle the requirement of persisting data for this app. Also this app does'nt require transactions. MongoDb cloud is also suitable in the case where one has to shared MongoDB collection. So following are the main reasons.
1) Unstructure data
2) No transaction involved
3) Collection of the projects can be shared with ease and makes the assessment less exhausting.

**POSTMAN END POINT COLLECTION LINK**
Link: https://documenter.getpostman.com/view/11312541/TzXtJfgj

**Validation**
*Checks field (Not null, email, length, etc)
*Validates ObjectId
*For internal server fault, it sends Internal server error status
