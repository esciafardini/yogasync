(ns yogasync.views
  (:require
   ["@fullcalendar/daygrid" :default dayGridPlugin]
   ["@fullcalendar/react" :default FullCalendar]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [yogasync.app-state :as app-state]
   [yogasync.yoga-classes :as yoga-classes]))

(defn calendar-ui []
  (let [app-name (re-frame/subscribe [::app-state/app-name])
        classes (re-frame/subscribe [::yoga-classes/classes])]
    [:<>
   [:h1.rainbow-text @app-name]
   [(reagent/adapt-react-class FullCalendar)
    {:plugins [dayGridPlugin]
     :events @classes}]]))
