#!/bin/sh

# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

BuildForJDK()
{
	SYSROOT=$1
	JDK_ID=$2
	JDK_VER=$3
	echo $SYSROOT $JDK_ID $SYSROOT/System/Library/Frameworks/JavaVM.framework/Versions/$JDK_VER/Headers

	CC_OPTS="-c -isystem $SYSROOT/System/Library/Frameworks/JavaVM.framework/Versions/$JDK_VER/Headers \
        -pipe -Wno-trigraphs -fpascal-strings -fasm-blocks \
	-Os -Wunused-variable -fmessage-length=0 -mmacosx-version-min=10.4 -Wmost \
	-Wno-four-char-constants -Wno-unknown-pragmas -isysroot $SYSROOT -DLINUX"

	LD_OPTS="-framework JavaVM  -Wl,-single_module -compatibility_version 1 -current_version 1 \
	-dynamiclib -mmacosx-version-min=10.4 -isysroot $SYSROOT"

	SOURCES="../src-jdk15/class_file_cache.c \
	../src-jdk15/attach.c \
	../src-jdk15/Classes.c \
	../src-jdk15/HeapDump.c \
	../src-jdk15/Timers.c \
	../src-jdk15/GC.c \
	../src-jdk15/Threads.c \
	../src-jdk15/Stacks.c \
	../src-jdk15/common_functions.c"

	OBJ_FILES="class_file_cache.o \
	attach.o \
	Classes.o \
	HeapDump.o \
	Timers.o \
	GC.o \
	Threads.o \
	Stacks.o \
	common_functions.o"

	BuildForArch "ppc"
        BuildForArch "ppc64"
	BuildForArch "i386"
        BuildForArch "x86_64"

	lipo -create libprofilerinterface_ppc.jnilib libprofilerinterface_i386.jnilib \
	libprofilerinterface_ppc64.jnilib libprofilerinterface_x86_64.jnilib \
	-output ../../release/lib/deployed/$JDK_ID/mac/libprofilerinterface.jnilib

	rm *.jnilib
}

BuildForArch()
{
        ARCH=$1
        echo "ARCH "$ARCH

        gcc-4.0 $CC_OPTS -arch $ARCH $SOURCES
        gcc-4.0 $LD_OPTS -arch $ARCH -o libprofilerinterface_$ARCH.jnilib $OBJ_FILES

        rm *.o
}

BuildForJDK "/Developer/SDKs/MacOSX10.4u.sdk" "jdk15" "1.5"
BuildForJDK "/Developer/SDKs/MacOSX10.5.sdk" "jdk16" "1.6"

