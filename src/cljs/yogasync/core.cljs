(ns yogasync.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [yogasync.events :as events]
   [yogasync.views :as views]))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/calendar-ui] root-el)))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (mount-root))

