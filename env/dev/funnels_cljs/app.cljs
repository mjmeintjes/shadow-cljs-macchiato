 (ns ^:figwheel-always funnels-cljs.app
  (:require
    [funnels-cljs.core :as core]
    [cljs.nodejs :as node]
    [mount.core :as mount]))

(mount/in-cljc-mode)

(cljs.nodejs/enable-util-print!)

(.on js/process "uncaughtException" #(js/console.error %))

(set! *main-cli-fn* core/main)

(def main core/main)
