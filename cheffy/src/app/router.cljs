(ns app.router 
  (:require [bidi.bidi :as bidi]
           [pushy.core :as pushy]
           [re-frame.core :as rf]))

; make a unique key for each unique route
(def routes ["/" {"" :recipes
                  "become-a-chef" :become-a-chef
                  "saved/"        :saved
                  "recipes/"      {"" :recipes
                                   [:recipe-id] :recipe}
                  "inbox/"        {"" :inboxes
                                   [:inbox-id] :inbox}
                  "profile"       :profile
                  "sign-up"       :sign-up
                  "log-in"        :log-in}])

(def history 
  (let [dispatch #(rf/dispatch [:route-changed %])
        match #(bidi/match-route routes %)]
    (pushy/pushy dispatch match)))

(defn start!
  "adds event listeners to on-click events and manages html push state"
  []
  (pushy/start! history))

(defn path-for ;; (router/path-for :save)
  "processes routes"
  [route]
  (bidi/path-for routes route))

(defn set-token!
  "Sets the address bar token"
  [token]
  ;; history is all of the events that have happened since we started our router
  (pushy/set-token! history token))