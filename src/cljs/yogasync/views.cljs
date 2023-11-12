(ns yogasync.views
  (:require
   ["@fullcalendar/daygrid" :default dayGridPlugin]
   ["@fullcalendar/rrule" :default rrulePlugin]
   ["@fullcalendar/react" :default FullCalendar]
   [re-frame.core :as rf]
   [reagent.core :as reagent]
   [yogasync.app-state :as app-state]
   [yogasync.yoga-classes :as yoga-classes]))

(defn calendar-ui []
  (let [app-name (rf/subscribe [::app-state/app-name])
        users (rf/subscribe [::app-state/users])
        classes (rf/subscribe [::yoga-classes/classes])]
    [:<>
     [:h1.logo-text @app-name]
     [:div (str @users)]
     [(reagent/adapt-react-class FullCalendar)
      {:plugins [dayGridPlugin rrulePlugin]
       :initialView "dayGridMonth"
       :eventClick (fn [info]
                     (.log js/console (-> info
                                          .-event
                                          .-extendedProps
                                          .-description)))
       :headerToolbar {:left "prev,next",
                       :center "title",
                       :right "dayGridMonth,dayGridWeek,dayGridDay"}
       :events @classes}]]))
