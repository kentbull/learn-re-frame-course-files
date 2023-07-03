(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db reg-fx]]
            [app.router :as router]))

(reg-fx
 :navigate-to ;; 
 (fn [{:keys [path]}]
   (router/set-token! path)))

(reg-event-db
 :set-active-nav ;; (rf/dispatch [:set-active-nav value])
 (fn [db [_event active-nav]]
   (assoc-in db [:nav :active-nav] active-nav)))


(reg-event-db
 :route-changed
(fn [db [_ {:keys [handler]}]]
  (assoc-in db [:nav :active-page] handler)))