(ns app.core
  (:require ;[reagent.core :as r]
   [reagent.dom :as rd]
   [re-frame.core :as rf]
   [app.db]
   [app.theme :refer [cheffy-theme]]
   ["@smooth-ui/core-sc" :refer [Normalize Button ThemeProvider]]))

(defn app
  []
  [:<>
   [:> Normalize]
   [:> ThemeProvider {:theme cheffy-theme}
    [:> Button {:variant "primary"} "hello"]
    [:div "Cheffy"]]])

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (rd/render [app]
             (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
