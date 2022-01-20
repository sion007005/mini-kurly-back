package com.sion.minikurlyback.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class OneItemParser {
    public ParsedItem parse(String url) throws IOException {
        if (url.contains("error")) {
            return null;
        }

        Document doc = Jsoup.connect(url).get();

        ParsedItem parsedItem = new ParsedItem();
        Elements imageElements = doc.select("#container div.item-topinfowrap div.thumb-gallery ul.viewer li.bigsmile img");

        String imageUrl = "";
        for (Element imageElement : imageElements) {
            if(imageElement.hasClass("box__bigs-tag")) {
                continue;
            }

            imageUrl = imageElement.attr("src");
        }

        String name = doc.select("#itemcase_basic h1.itemtit").text();
        String originalPrice = doc.select("#itemcase_basic p.price span.price_original").text();
        String salePrice = doc.select("#itemcase_basic p.price strong.price_real").text();

        parsedItem.setName(name);

        if (Objects.nonNull(originalPrice) || originalPrice.isEmpty() || originalPrice.isBlank()) {
            return null;
        }

        parsedItem.setOriginalPrice(Integer.parseInt(originalPrice));
        parsedItem.setSalePrice(Integer.parseInt(salePrice));
        parsedItem.setImageUrl(imageUrl);

        return parsedItem;
    }
}
