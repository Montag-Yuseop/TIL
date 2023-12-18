package com.hub.toy.data.dao.impl;

import com.hub.toy.data.dao.ShortUrlDao;
import com.hub.toy.data.entity.ShortUrlEntity;
import com.hub.toy.data.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortUrlDaoImpl implements ShortUrlDao {

    private final ShortUrlRepository shortUrlRepository;


    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrl) {
        log.info("SAVE");
        ShortUrlEntity foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }
    @Override
    public ShortUrlEntity getOriginalUrl(String shortUrl) {
        return null;
    }

    @Override
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl) {
        return null;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {

    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {

    }
}
