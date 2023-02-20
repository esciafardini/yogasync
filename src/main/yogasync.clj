(ns main.yogasync
  (:require
    [cheshire.core :as cheshire]
    [clj-http.client :as client])
  (:import (java.time Instant)))

(+ 3 2)

; const date = new Date();

; let day = date.getDay();
; let dayNumber = date.getDate();
; let month = date.getMonth() + 1;
; let year = date.getFullYear();
(defn now [] (new java.util.Date))
(str (now))

(def weekdays ["Sun" "Mon" "Tue" "Wed" "Thu" "Fri" "Sat"])

; let dayName = weekday[day];

(def months ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"])

; let monthName = months[month];

; // This arrangement can be altered based on how we want the date's format to appear.
(defn date-string [day month date-n year]
  (format "%s %s-%s-%s" day month date-n year))
; let currentDateYU = `${dayName} ${monthName}-${dayNumber}-${year}`;

(defn http-request [f url query-params headers]
  (f url
     {:accept :json
      :query-params query-params
      :headers headers}))


(client/post "https://www.vagaro.com/us03/websiteapi/homepage/getavailablemultievents"
             {:accept :json
              :query-params
              {:businessID "188913"
               :isFromOffline "false"
               :promotionID "0"
               :sEndDate "Sat Feb-25-2023"
               :sSatrtDate "Sun Feb-19-2023"
               :searchdetails "<searchdetails><searchdetail><serviceid>-2</serviceid><spid>-2</spid><streamingstatus>1</streamingstatus></searchdetail></searchdetails>"
               :userID "116806159"}})

(client/post "https://www.vagaro.com/us03/websiteapi/homepage/getavailablemultievents"
             {:accept :json
              :query-params
              {"businessID" "188913"
               "isFromOffline" "false"
               "promotionID" "0"
               "sEndDate" "Sat Feb-25-2023"
               "sSatrtDate" "Sun Feb-19-2023"
               "searchdetails" "<searchdetails><searchdetail><serviceid>-2</serviceid><spid>-2</spid><streamingstatus>1</streamingstatus></searchdetail></searchdetails>"
               "userID" "116806159"}})



(cheshire/parse-string "{\"foo\":\"bar\"}")

(-> (client/get "https://api.glofox.com/2.0/events?end=1678078799&include=trainers,facility,program,users_booked&page=1&private=false&sort_by=time_start&start=1676847513"
            {:accept :json
             :headers
             {:Authorization "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJfIiwiZXhwIjoxNjc4OTY5MTgwLCJpYXQiOjE2NzY1NDk5ODAsImlzcyI6Il8iLCJ1c2VyIjp7Il9pZCI6Imd1ZXN0IiwibmFtZXNwYWNlIjoidXBkb2dzdHVkaW9zIiwiYnJhbmNoX2lkIjoiNjIzZTM3ODJjY2I3MjUwNTQxMTc2M2U1IiwiZmlyc3RfbmFtZSI6Ikd1ZXN0IiwibGFzdF9uYW1lIjoiVXNlciIsInR5cGUiOiJHVUVTVCIsImlzU3VwZXJBZG1pbiI6ZmFsc2V9fQ.tmmPxXWBJUZfkB07NbnwhEoIwkSLoWIbHnDHJ5qivYo" }})
    :body
    cheshire/parse-string)

    { */
      headers: { */
        Authorization: */
          'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJfIiwiZXhwIjoxNjc4OTY5MTgwLCJpYXQiOjE2NzY1NDk5ODAsImlzcyI6Il8iLCJ1c2VyIjp7Il9pZCI6Imd1ZXN0IiwibmFtZXNwYWNlIjoidXBkb2dzdHVkaW9zIiwiYnJhbmNoX2lkIjoiNjIzZTM3ODJjY2I3MjUwNTQxMTc2M2U1IiwiZmlyc3RfbmFtZSI6Ikd1ZXN0IiwibGFzdF9uYW1lIjoiVXNlciIsInR5cGUiOiJHVUVTVCIsImlzU3VwZXJBZG1pbiI6ZmFsc2V9fQ.tmmPxXWBJUZfkB07NbnwhEoIwkSLoWIbHnDHJ5qivYo', */
      }, */
    } */

;axios */

;   .post( */
;     'https://www.vagaro.com/us03/websiteapi/homepage/getavailablemultievents', */
;     { */
;     } */
;   ) */
;   .then(({ data }) => console.log(data)); */

; axios */
;   .get( */
;     `https://api.glofox.com/2.0/events?end=${end}&include=trainers,facility,program,users_booked&page=1&private=false&sort_by=time_start&start=${start}`, */
;     { */
;       headers: { */
;         Authorization: */
;           'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJfIiwiZXhwIjoxNjc4OTY5MTgwLCJpYXQiOjE2NzY1NDk5ODAsImlzcyI6Il8iLCJ1c2VyIjp7Il9pZCI6Imd1ZXN0IiwibmFtZXNwYWNlIjoidXBkb2dzdHVkaW9zIiwiYnJhbmNoX2lkIjoiNjIzZTM3ODJjY2I3MjUwNTQxMTc2M2U1IiwiZmlyc3RfbmFtZSI6Ikd1ZXN0IiwibGFzdF9uYW1lIjoiVXNlciIsInR5cGUiOiJHVUVTVCIsImlzU3VwZXJBZG1pbiI6ZmFsc2V9fQ.tmmPxXWBJUZfkB07NbnwhEoIwkSLoWIbHnDHJ5qivYo', */
;       }, */
;     } */
;   ) */
;   .then(({ data }) => */
;     console.log( */
;       data.data */
;         .map(({ name, trainers, time_start, duration }) => ({ */
;           name, */
;           trainers, */
;           time_start, */
;           duration, */
;         })) */
;         .map((v) => ({ ...v, readabletime: s(v['time_start'])})) */
;     ) */
;   ); */
;*/

; axios
;   .get(`https://api.glofox.com/2.0/staff?sort_by=first_name&type=TRAINER`, {
;     headers: {
;       Authorization:
;         'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJfIiwiZXhwIjoxNjc4OTY5MTgwLCJpYXQiOjE2NzY1NDk5ODAsImlzcyI6Il8iLCJ1c2VyIjp7Il9pZCI6Imd1ZXN0IiwibmFtZXNwYWNlIjoidXBkb2dzdHVkaW9zIiwiYnJhbmNoX2lkIjoiNjIzZTM3ODJjY2I3MjUwNTQxMTc2M2U1IiwiZmlyc3RfbmFtZSI6Ikd1ZXN0IiwibGFzdF9uYW1lIjoiVXNlciIsInR5cGUiOiJHVUVTVCIsImlzU3VwZXJBZG1pbiI6ZmFsc2V9fQ.tmmPxXWBJUZfkB07NbnwhEoIwkSLoWIbHnDHJ5qivYo',
;     },
;   })
;   .then(({ data }) =>
;     console.log(
;       data.data.map(({ _id, name }) => ({
;         _id,
;         name,
;       }))
;     )
;   );

(client/get "http://example.com/resources/3" {:accept :json :query-params {"q" "foo, bar"}})

(defn plus [x y]
  (+ x y))

(defn divide [x y]
  (/ x y))

(defn run [& _args]
  (println (format "2 + 2 is %s" (plus 2 2)))
  (println (format "4 / 2 is %s" (divide 4 2))))
