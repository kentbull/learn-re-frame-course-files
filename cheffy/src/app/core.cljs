(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rd]))

(defn app
  []
  [:div "Cheffy"])

(defn ^:dev/after-load start
  []
  (rd/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
