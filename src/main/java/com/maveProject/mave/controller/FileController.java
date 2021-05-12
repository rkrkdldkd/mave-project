package com.maveProject.mave.controller;

import com.maveProject.mave.dto.FileDto;
import com.maveProject.mave.service.FileService;
import com.maveProject.mave.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, Model model) {
        try {
            String originalFilename = files.getOriginalFilename(); // 파일 이름 가져오기
            String filename = new MD5Generator(originalFilename).toString(); // 파일 이름 기반 hash값 생성?
            String savePath = System.getProperty("user.dir") + "\\mave-photoBook"; // mave-photoBook 폴더에 파일 저장

            /* mave-photoBook 폴더가 없다면 생성 */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename; // 파일 저장될 위치
            files.transferTo(new File(filePath)); // files로 넘어온 파일을 filePath 위치에 넘긴다.

            FileDto fileDto = new FileDto();    // 넘어온 file을 dto로 바꿔주는 작업
            fileDto.setOrigFilename(originalFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
            Long fileId = fileService.saveFile(fileDto); // fileDto를 DB에 저장한다.

            model.addAttribute("file", fileDto);    // fileDto를 result.html에 넘긴다.
            model.addAttribute("fileId", fileId);    // 저장한 fileId를 result.html에 넘긴다.

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId); // DB에 저장된 정보 가져온다.
        Path path = Paths.get(fileDto.getFilePath()); // 가져온 정보에 저장된 path 가져온다.
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }


}
