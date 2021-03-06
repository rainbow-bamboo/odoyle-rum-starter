(ns components.example.renders
 (:require [odoyle.rules :as o]
           [odoyle.rum :as orum]
           [components.example.themes :as t]))

(defn insert! [*session id attr->value]
  (swap! *session
         (fn [session]
           (-> session
               (o/insert id attr->value)
               o/fire-rules))))

(def render-rules
  (orum/ruleset
   {class-based-theme-example
    [:what
     [::t/global ::t/active-theme active-theme]
     :then
     (let [*session (orum/prop)]
       [:div
        {:class active-theme}
        "This has the theme: " active-theme])]

    theme-selector
    [:what
     [::t/global ::t/active-theme active-theme]
      :then
      (let [*session (orum/prop)]
        [:select
         {:id "theme-selector"
          :value active-theme
          :on-change (fn [e]
                       (insert! *session ::t/global {::t/active-theme  (-> e .-target .-value)}))}
         [:option {:value "light-theme"} "light-theme"]
         [:option {:value "seaside-theme"} "seaside-theme"]
         [:option {:value "dark-theme"} "dark-theme"]
         [:option {:value "forest-theme"} "forest-theme"]])]}))
