package softuni.bg.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.repository.PictureRepository;
import softuni.bg.pathfinder.service.PictureService;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllUrls() {
        return this.pictureRepository.findAllUrls();
    }
}
