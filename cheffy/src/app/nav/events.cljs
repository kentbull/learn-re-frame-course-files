(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db]]))


(reg-event-db
 :set-active-nav ;; (rf/dispatch [:set-active-nav value])
 (fn [db [_event active-nav]]
   (assoc-in db [:nav :active-nav] active-nav)))

