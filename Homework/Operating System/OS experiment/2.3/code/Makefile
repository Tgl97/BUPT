ifneq ($(KERNELRELEASE),)
#kbuild syntax.dependencyrelationshsip of files and target modules are listed here.
obj-m := proc_clock.o
else
	PWD := $(shell pwd)
	KVER := $(shell uname -r)
	KDIR := /lib/modules/$(KVER)/build
all:
	$(MAKE) -C $(KDIR) M=$(PWD) modules
clean:
	rm -rf *.ko *.o *.mod.o *.mod.c *.symvers
endif
