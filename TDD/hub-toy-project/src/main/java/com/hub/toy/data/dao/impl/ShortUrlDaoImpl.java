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
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.save(shortUrl);
        return foundShortUrlEntity;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrlEntity;
    }
    @Override
    public ShortUrlEntity getOriginalUrl(String shortUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrlEntity;
    }

    @Override
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl) {
        return null;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrlEntity);
    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
        log.info("url ={]", originalUrl.toString());
        shortUrlRepository.delete(foundShortUrlEntity);
    }
}
