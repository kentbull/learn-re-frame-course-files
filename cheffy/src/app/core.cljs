(ns app.core
  (:require ;[reagent.core :as r]
   [reagent.dom :as rd]
   [re-frame.core :as rf]
   [app.db]
   ;; -- nav --
   [app.nav.views.nav :refer [nav]]
   [app.nav.events]
   [app.nav.subs]

   [app.theme :refer [cheffy-theme]]
   ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider]]))

(defn app
  []
  [:<>
   [:> Normalize]
   [:> ThemeProvider {:theme cheffy-theme}
    [nav]
    "Cheffy "]])

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (rd/render [app]
             (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
