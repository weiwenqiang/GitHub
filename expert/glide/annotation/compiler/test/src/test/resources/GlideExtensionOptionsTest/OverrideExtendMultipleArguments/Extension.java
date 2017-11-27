package com.bumptech.glide.test;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

@GlideExtension
public final class Extension {

  private Extension() {
    // Utility class.
  }

  @GlideOption(override = GlideOption.OVERRIDE_EXTEND)
  public static RequestOptions override(RequestOptions requestOptions, int width, int height) {
    return requestOptions
        .override(width, height)
        .centerCrop();
  }
}
