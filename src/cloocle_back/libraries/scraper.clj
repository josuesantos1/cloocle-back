(ns cloocle-back.libraries.scraper
  (:require [net.cgrand.enlive-html :as html])
  (:import java.net.URL))

(defn- get-title
  [content]
  (html/select content [:title]))

(defn- get-meta-tags
  [content]
  (html/select content [:meta]))

(defn- get-description
  [content]
  (let [meta-tags (get-meta-tags content)
        description (map (fn [meta-tag]
                           (let [tag (get-in meta-tag [:attrs :name])]
                             (when (= tag "description")
                               (get-in meta-tag [:attrs :content])))) meta-tags)]
    (remove nil? description)))

(defn- get-text
  [content]
  (let [text (html/select content [:p])]
    (map #(html/text %) text)))

(defn get-contents
  [urls]
  (map #(let [url (URL. %)
              content (html/html-resource url)
              title (get-title content)
              description (get-description content)
              text (get-text content)]
          {:url (str url)
           :title title
           :description description
           :text text}) urls))
