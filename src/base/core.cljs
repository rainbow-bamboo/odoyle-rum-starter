(ns base.core
  (:require [rum.core :as rum]
            [odoyle.rules :as o]
            [components.shell.renders :as r]
            [components.example.themes :as t]
            [components.example.renders :as or]))

(def initial-session
  (-> (reduce o/add-rule (o/->session) (concat r/render-rules or/render-rules))
      (o/insert ::t/global {::t/active-theme "light-theme"})
      o/fire-rules))

(def *rules-session (atom initial-session))


(rum/defc app []
  (r/app-root *rules-session))


(defn ^:export main
  []
  (rum/hydrate (app) (js/document.querySelector "#app")))
