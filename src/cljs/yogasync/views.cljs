(ns yogasync.views
  (:require
   ["@fullcalendar/daygrid" :default dayGridPlugin]
   ["@fullcalendar/react" :default FullCalendar]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [yogasync.subs :as subs]))

(defn calendar-ui []
  (let [app-name (re-frame/subscribe [::subs/app-name])]
    [:<>
   [:h1.rainbow-text @app-name]
   [(reagent/adapt-react-class FullCalendar)
    {:plugins [dayGridPlugin]
     :events [{:title "4:30PM - Keith Golden what happens when its sooooo long bro ahhha", :date "2023-10-13"}
              {:title "12:00pm - Tami Jacobs", :date "2023-10-12"}]}]]))
