(ns components.shell.renders
  (:require [odoyle.rules :as o]
            [odoyle.rum :as orum]
            [components.example.renders :as er]
            [components.example.themes :as t]))


(def render-rules
  (orum/ruleset
   {app-root
    [:what
     [::t/global ::t/active-theme active-theme]
     :then
     (let [*session (orum/prop)]
       [:div#app-root
        {:class active-theme}
        [:header
         [:h1 "Odoyle Rum Starter Application"]]
        (er/theme-selector *session)
        (er/class-based-theme-example *session)])]}))
