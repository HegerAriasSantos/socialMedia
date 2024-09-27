package com.projects.socialmedia.common.interfaces;

public interface IMapper<A, B> {
  B mapTo(A a);

  A mapFrom(B b);
}
