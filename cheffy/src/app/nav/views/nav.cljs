(ns app.nav.views.nav
  (:require [app.nav.views.authenticated :refer [authenticated]]
            [app.nav.views.public :refer [public]]
            [re-frame.core :as rf]))

(defn nav
  []
  (let [user @(rf/subscribe [:logged-in?])]
    (if user
      [authenticated]
      [public])))