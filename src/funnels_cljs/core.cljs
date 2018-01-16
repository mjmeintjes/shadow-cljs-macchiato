(ns funnels-cljs.core
  (:require
    [funnels-cljs.config :refer [env]]
    [funnels-cljs.middleware :refer [wrap-defaults]]
    [funnels-cljs.routes :refer [router]]
    [macchiato.server :as http]
    [macchiato.middleware.session.memory :as mem]
    [mount.core :as mount :refer [defstate]]
    [taoensso.timbre :refer-macros [log trace debug info warn error fatal]]))

(defonce server-ref
  (volatile! nil))

(defn server []
  (mount/start)
  (let [host (or (:host @env) "127.0.0.1")
        port (or (some-> @env :port js/parseInt) 3000)]
    (vreset!
     server-ref
     (http/start
      {:handler    (wrap-defaults router)
       :host       host
       :port       port
       :on-success #(info "funnels-cljs started on" host ":" port)}))))

(defn start
  "Hook to start. Also used as a hook for hot code reload."
  []
  (js/console.warn "start called")
  (server))

(defn stop
  "Hot code reload hook to shut down resources so hot code reload can work"
  [done]
  (js/console.warn "stop called")
  (when-some [srv @server-ref]
    (.close srv
            (fn [err]
              (js/console.log "stop completed" err)
              (done)))))

(defn main
  []
  (start))

(js/console.log "__filename" js/__filename)
