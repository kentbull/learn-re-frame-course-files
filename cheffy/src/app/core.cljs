(ns app.core
  (:require ;[reagent.core :as r]
   [reagent.dom :as rd]
   [re-frame.core :as rf]
   [app.db]
   [app.router :as router]
   ;; -- auth --
   [app.auth.views.profile :refer [profile]]
   [app.auth.views.sign-up :refer [sign-up]]
   [app.auth.views.log-in :refer [log-in]]
   [app.auth.events]
   [app.auth.subs]
   ;; -- become-a-chef --
   [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
   ;; -- inbox -- 
   [app.inbox.views.inboxes :refer [inboxes]]
   ;; -- nav --
   [app.nav.views.nav :refer [nav]]
   [app.nav.events]
   [app.nav.subs]
   ;; -- recipes --
   [app.recipes.views.recipes :refer [recipes]]

   [app.theme :refer [cheffy-theme]]
   ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Grid Row Col]]))

(defn pages 
  "Select a page based on keyword"
  [page-name]
  (case page-name 
    :profile [profile]
    :sign-up [sign-up]
    :log-in [log-in]
    :become-a-chef [become-a-chef]
    :inboxes [inboxes]
    :recipes [recipes]
    [recipes]))

(defn app
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    [:<>
     [:> Normalize]
     [:> ThemeProvider {:theme cheffy-theme}
      [:> Grid {:fluid false}
       [:> Row
        [:> Col
         [nav]
         [pages active-nav]]]]]]))

(defn ^:dev/after-load start
  [] 
  (rd/render [app]
             (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
