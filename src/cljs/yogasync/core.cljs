(ns yogasync.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [yogasync.app-state :as app-state]
   [yogasync.views :as views]))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (rf/dispatch-sync [::app-state/initialize-db])
  (rf/dispatch [::app-state/get-users-http])
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/calendar-ui] root-el)))

(defn ^:export init []
  (mount-root))
