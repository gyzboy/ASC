package com.gyz.androidsamplecode.store;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStoreFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        opts.add("写入文件到外部私有存储");
        opts.add("读取文件从外部私有存储");
        opts.add("写入文件到外部公共存储");
        opts.add("读取文件从外部公共存储");
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        sb.append("公共目录：如Downloads、Pictures、Music等，这些目录可以被所有应用访问\n").append("应用私有目录：如getExternalFilesDir()返回的目录，仅当前应用可以访问，应用卸载时会被删除\n");
        switch (index) {
            case 0:
                writeFileToExternalStorage();
                break;
            case 1:
                readFileFromExternalStorage();
                break;
            case 2:
                writeFileToPublicDirectory();
                break;
            case 3:
                readFileFromPublicDirectory();
                break;
        }
        content.setText(sb.toString());
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private void writeFileToExternalStorage() {
        if (isExternalStorageWritable()) {
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "example.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                String content = "Hello, External Storage!";
                fos.write(content.getBytes());
            } catch (IOException e) {
                sb.append(e.getMessage());
            }
            sb.append("FilePath:").append(file.getAbsoluteFile());
        }
    }

    private void readFileFromExternalStorage() {
        if (isExternalStorageReadable()) {
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "example.txt");
            try (FileInputStream fis = new FileInputStream(file)) {
                int ch;
                while ((ch = fis.read()) != -1) {
                    sb.append((char) ch);
                }
            } catch (IOException e) {
                sb.append(e.getMessage());
            }
        }
    }

    private void writeFileToPublicDirectory() {
        if (isExternalStorageWritable()) {
            File publicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(publicDir, "example.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                String content = "Hello, Public Directory!";
                fos.write(content.getBytes());
                sb.append("File written to: ").append(file.getAbsolutePath());
            } catch (IOException e) {
                sb.append(e.getMessage());
            }
        } else {
            sb.append("External storage not writable");
        }
    }

    private void readFileFromPublicDirectory() {
        if (isExternalStorageReadable()) {
            File publicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(publicDir,"example.txt");
            try (FileInputStream fis = new FileInputStream(file)) {
                int ch;
                while ((ch = fis.read()) != -1) {
                    sb.append((char) ch);
                }
            } catch (IOException e) {
                sb.append(e.getMessage());
            }
        }
    }
}
