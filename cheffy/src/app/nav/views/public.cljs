(ns app.nav.views.public
  (:require ["@smooth-ui/core-sc" :refer [Box]]
            [re-frame.core :as rf]))

(defn public
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :recipes
                    :name "Recipes"
                    :href "#recipes"
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])} 
                   {:id :become-a-chef
                    :name "Chef"
                    :href "#become-a-chef"
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :sign-up
                    :name "Sign up"
                    :href "#sign-up"
                    :dispatch #(rf/dispatch [:set-active-nav :sign-up])}
                   {:id :log-in
                    :name "Log In"
                    :href "#log-in"
                    :dispatch #(rf/dispatch [:set-active-nav :log-in])}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1}

     (for [{:keys [id name href dispatch]} nav-items]

       [:> Box {:key id
                :as "a"
                :href href
                :ml 2
                :pb 10
                :border-bottom (when (= active-nav id) "2px solid #102A43")
                :on-click dispatch}
        name])]))