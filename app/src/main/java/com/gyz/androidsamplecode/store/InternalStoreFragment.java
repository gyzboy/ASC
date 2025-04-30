package com.gyz.androidsamplecode.store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStoreFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("获取内部存储目录");
        opts.add("普通目录文件写入");
        opts.add("普通目录文件读取");
        opts.add("普通目录文件删除");
        opts.add("普通目录文件列表");
        opts.add("缓存目录文件写入");
        opts.add("缓存目录文件读取");
    }

    @Override
    public void handleClick(View v) {
        sb.setLength(0);
        if (getActivity() == null) {
            return;
        }
        String filename = "myfile.txt";
        switch ((int) v.getTag()) {
            case 0:
//            File filesDir = getFilesDir();
//            File cacheDir = getCacheDir();
//            File customDir = getDir("custom", Context.MODE_PRIVATE);
                sb.append("filesDir:").append(getActivity().getFilesDir().getAbsoluteFile()).append("\n\n")
                        .append("cacheDir:").append(getActivity().getCacheDir().getAbsoluteFile()).append("\n\n")
                        .append("customDir:").append(getActivity().getDir("custom", Context.MODE_PRIVATE));
                break;
            case 1:
                // 写入getFilesDir目录下
                String fileContents = "Hello World!";
                try (FileOutputStream fos = getActivity().openFileOutput(filename, Context.MODE_PRIVATE)) {
                    fos.write(fileContents.getBytes());
                } catch (IOException e) {
                    sb.append(e.getMessage());
                }
                break;
            case 2:
                try (FileInputStream fis = getActivity().openFileInput(filename);
                     InputStreamReader isr = new InputStreamReader(fis);
                     BufferedReader br = new BufferedReader(isr)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    sb.append(e.getMessage());
                }
                break;
            case 3:
                boolean deleted = getActivity().deleteFile(filename);
                sb.append("deleteFile success: ").append(deleted);
                break;
            case 4:
                String[] files = getActivity().fileList();
                for (String file : files) {
                    sb.append(file).append("\n");
                }
                break;
            case 5:
                writeToCache("cacheFile.txt","hello world");
                break;
            case 6:
                sb.append(readFromCache("cacheFile.txt"));
                break;
        }
        content.setText(sb.toString());
    }

    private void writeToCache(String filename, String content) {
        File cacheDir = getActivity().getCacheDir(); // 获取内部缓存目录
        File file = new File(cacheDir, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromCache(String filename) {
        File cacheDir = getActivity().getCacheDir(); // 获取内部缓存目录
        File file = new File(cacheDir, filename);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
