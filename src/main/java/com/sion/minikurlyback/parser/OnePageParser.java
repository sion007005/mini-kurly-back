package com.sion.minikurlyback.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnePageParser {
    private final OneItemParser oneItemParser;

    public List<ParsedItem> parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        List<ParsedItem> parsedItems = new ArrayList<>();

        Elements elements = doc.select("#mainLayout");

        for (Element element : elements) {
            Elements itemElement = element.select("div.box__component-itemcard--general");

            if (itemElement.size() == 0) {
                continue;
            }

            ParsedItem parsedItem = new ParsedItem();
            String itemUrl = itemElement.select("div.box__item-container div.box__image a").attr("href");

            parsedItem = oneItemParser.parse(itemUrl);

            if (Objects.nonNull(parsedItem)) {
                parsedItems.add(parsedItem);
            }
        }


        return parsedItems;
    }

}

